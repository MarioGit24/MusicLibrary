import { Alert, Backdrop, Box, Button, Fade, FormControl, InputLabel, MenuItem, Modal, Select, TextField, Typography, CircularProgress } from "@mui/material";
import { useEffect, useState } from "react";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "70%",
  maxHeight: "85vh",
  bgcolor: "#1f1f1f",
  borderRadius: "5px",
  boxShadow: 10,
  p: 4,
  display: "flex",
  flexDirection: "column",
};

interface Props {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<any>>;
  type: string;
}

function ModalMenu({ open, setOpen, type }: Props) {
  // Fel- och laddningsstatus
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  
  const [artists, setArtists] = useState<{ id: number; name: string }[]>([]);
  const [selectedArtistId, setSelectedArtistId] = useState<string>("");

  // Formulär-states
  const [artistFormData, setArtistFormData] = useState({ name: "" });
  const [releaseFormData, setReleaseFormData] = useState({ title: "", duration: "" });
  const [albumFormData, setAlbumFormData] = useState({
    title: "",
    songs: [{ title: "", duration: "" }, { title: "", duration: "" }, { title: "", duration: "" }],
  });
  const [epFormData, setEpFormData] = useState({
    title: "",
    songs: [{ title: "", duration: "" }, { title: "", duration: "" }],
  });

  // Hämta artister när modalen öppnas
  useEffect(() => {
    const fetchArtists = async () => {
      try {
        const response = await fetch("http://localhost:8082/artists");
        if (!response.ok) throw new Error("Kunde inte hämta artistlistan.");
        const data = await response.json();
        setArtists(data);
      } catch (err) {
        console.error(err);
        setError("Kunde inte ansluta till Artist-Service (port 8082).");
      }
    };
    if (open) {
      setError(null);
      fetchArtists();
    }
  }, [open]);

  const handleClose = () => {
    setOpen(false);
    setError(null);
    setLoading(false);
  };

  // --- API LOGIK ---

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    setLoading(true);

    const t = type.toLowerCase();

    try {
      if (t === "artist") {
        const res = await fetch("http://localhost:8082/artists", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ name: artistFormData.name }),
        });
        if (!res.ok) throw new Error("Misslyckades att skapa artist.");
        const newArtist = await res.json();
        
        // Enrolla till skivbolag
        await fetch(`http://localhost:8081/recordlabels/1/enroll-artist/${newArtist.id}`, { method: "PUT" });
      } 
      
      else if (t === "single") {
        if (!selectedArtistId) throw new Error("Du måste välja en artist för singeln!");

        const singleData = {
          title: releaseFormData.title,
          duration: parseInt(releaseFormData.duration) || 0,
          artistId: parseInt(selectedArtistId),
          recordlabelId: 1
        };

        const res = await fetch("http://localhost:8083/singles", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(singleData),
        });

        if (!res.ok) {
          const msg = await res.text();
          throw new Error(msg || "Servern nekade att skapa singeln (8083).");
        }
      } 
      
      else if (t === "album" || t === "ep") {
        if (!selectedArtistId) throw new Error("Välj en artist!");
        const isAlbum = t === "album";
        const data = isAlbum ? albumFormData : epFormData;

        const body = {
          title: data.title,
          artistId: parseInt(selectedArtistId),
          recordlabelId: 1,
          songsList: data.songs.map(s => ({ title: s.title, duration: parseInt(s.duration) || 0 }))
        };

        const endpoint = isAlbum ? "albums" : "eps";
        const res = await fetch(`http://localhost:8083/${endpoint}`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(body),
        });
        if (!res.ok) throw new Error(`Misslyckades att skapa ${t}.`);
      }

      // Om allt gick bra:
      handleClose();

    } catch (err: any) {
      setLoading(false);
      setError(err.message === "Failed to fetch" 
        ? "Nätverksfel: Kontrollera att dina Java-tjänster är igång." 
        : err.message);
    }
  };

  const currentType = type.toLowerCase();

  return (
    <Modal open={open} onClose={handleClose} closeAfterTransition slots={{ backdrop: Backdrop }}>
      <Fade in={open}>
        <Box sx={style}>
          <Typography variant="h5" sx={{ color: "#9e79a1ff", mb: 2, fontWeight: "bold" }}>
            Create {type.toUpperCase()}
          </Typography>

          {/* ALERT-BOX FÖR FELMEDDELANDEN */}
          {error && (
            <Alert severity="error" sx={{ mb: 3, bgcolor: "#310909", color: "#ff8a8a", border: "1px solid #631c1c" }}>
              {error}
            </Alert>
          )}

          <Box component="form" onSubmit={handleSubmit} sx={{ display: "flex", flexDirection: "column", gap: 2, overflowY: "auto" }}>
            
            {currentType === "artist" && (
              <TextField 
                label="Artist Name" 
                fullWidth 
                required
                value={artistFormData.name} 
                onChange={(e) => setArtistFormData({name: e.target.value})}
                sx={inputStyle}
              />
            )}

            {currentType === "single" && (
              <>
                <TextField label="Song Title" fullWidth required value={releaseFormData.title} onChange={(e) => setReleaseFormData({...releaseFormData, title: e.target.value})} sx={inputStyle} />
                <TextField label="Duration (seconds)" type="number" fullWidth required value={releaseFormData.duration} onChange={(e) => setReleaseFormData({...releaseFormData, duration: e.target.value})} sx={inputStyle} />
              </>
            )}

            {(currentType === "album" || currentType === "ep") && (
              <Box>
                <TextField label="Release Title" fullWidth required value={currentType === "album" ? albumFormData.title : epFormData.title} onChange={(e) => currentType === "album" ? setAlbumFormData({...albumFormData, title: e.target.value}) : setEpFormData({...epFormData, title: e.target.value})} sx={inputStyle} />
                <Typography sx={{ color: "#8e7990e4", mt: 2, mb: 1 }}>Tracklist:</Typography>
                {(currentType === "album" ? albumFormData.songs : epFormData.songs).map((song, index) => (
                  <Box key={index} sx={{ display: "flex", gap: 1, mb: 1 }}>
                    <TextField label={`Song ${index + 1}`} fullWidth size="small" value={song.title} onChange={(e) => {
                      const newSongs = currentType === "album" ? [...albumFormData.songs] : [...epFormData.songs];
                      newSongs[index].title = e.target.value;
                      currentType === "album" ? setAlbumFormData({...albumFormData, songs: newSongs}) : setEpFormData({...epFormData, songs: newSongs});
                    }} sx={inputStyle} />
                    <TextField label="Sec" type="number" size="small" sx={{...inputStyle, width: "120px"}} value={song.duration} onChange={(e) => {
                      const newSongs = currentType === "album" ? [...albumFormData.songs] : [...epFormData.songs];
                      newSongs[index].duration = e.target.value;
                      currentType === "album" ? setAlbumFormData({...albumFormData, songs: newSongs}) : setEpFormData({...epFormData, songs: newSongs});
                    }} />
                  </Box>
                ))}
              </Box>
            )}

            {currentType !== "artist" && (
              <FormControl variant="filled" fullWidth sx={selectStyle} required>
                <InputLabel sx={{ color: "#8e7990e4" }}>Select Artist</InputLabel>
                <Select value={selectedArtistId} onChange={(e) => setSelectedArtistId(e.target.value as string)}>
                  {artists.map((a) => <MenuItem key={a.id} value={a.id.toString()}>{a.name}</MenuItem>)}
                </Select>
              </FormControl>
            )}

            <Box sx={{ display: "flex", justifyContent: "flex-end", mt: 3 }}>
              <Button 
                type="submit" 
                disabled={loading}
                variant="contained"
                sx={{ bgcolor: "#8F6D92", "&:hover": { bgcolor: "#7a5c7d" }, px: 5, py: 1 }}
              >
                {loading ? <CircularProgress size={24} color="inherit" /> : `Create ${type}`}
              </Button>
            </Box>
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
}

const inputStyle = {
  background: "#d7d7d70d", borderRadius: 1,
  "& .MuiInputBase-input": { color: "#fff" },
  "& .MuiInputLabel-root": { color: "#8e7990e4" },
  "& .MuiOutlinedInput-root": { "& fieldset": { borderColor: "#ffffff1a" } }
};

const selectStyle = {
  background: "#d7d7d70d", borderRadius: 1,
  "& .MuiSelect-select": { color: "#fff" },
  "& .MuiInputLabel-root": { color: "#8e7990e4" }
};

export default ModalMenu;