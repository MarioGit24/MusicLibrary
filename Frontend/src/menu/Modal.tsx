import Backdrop from "@mui/material/Backdrop";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Fade from "@mui/material/Fade";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Modal from "@mui/material/Modal";
import Select from "@mui/material/Select";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import { useEffect, useState } from "react";

const style = {
  p: 3,
  boxShadow: 10,
  top: "50%",
  left: "50%",
  width: "70%",
  display: "flex",
  minHeight: "30%",
  borderRadius: "5px",
  position: "absolute",
  bgcolor: "#1f1f1f",
  flexDirection: "column",
  transform: "translate(-50%, -50%)",
};

const inputStyle = {
  background: "#d7d7d718",
  borderRadius: 2,
  mb: 1,
  input: {
    color: "#aea5afff",
    "&::placeholder": {
      color: "#8e7990e4",
    },
  },
  fieldset: { borderColor: "#aea5af23" },
  "& .MuiOutlinedInput-root": {
    "&.Mui-focused fieldset": {
      borderColor: "#aea5af23",
    },
  },
};

interface Props {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<any>>;
  type: string;
}

function ModalMenu({ open, setOpen, type }: Props) {
  const handleClose = () => setOpen(false);

  const [artists, setArtists] = useState<{ id: number; name: string }[]>([]);

  const [artistFormData, setArtistFormData] = useState({ name: "" });
  const [releaseFormData, setReleaseFormData] = useState({ title: "", duration: "" });
  const [albumFormData, setAlbumFormData] = useState({
    title: "",
    artistId: "",
    songs: [
      { title: "", duration: "" },
      { title: "", duration: "" },
      { title: "", duration: "" },
    ],
  });
  const [epFormData, setEpFormData] = useState({
    title: "",
    artistId: "",
    songs: [
      { title: "", duration: "" },
      { title: "", duration: "" },
    ],
  });

  useEffect(() => {
    const fetchArtists = async () => {
      try {
        const response = await fetch("http://localhost:8082/artists");
        const data = await response.json();
        setArtists(data);
      } catch (error) {
        console.error("Error fetching artists:", error);
      }
    };
    if (open) fetchArtists();
  }, [open]);

  // --- API FUNCTIONS ---

  async function createAndEnrollArtist() {
    try {
      const response = await fetch("http://localhost:8082/artists", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name: artistFormData.name }),
      });
      if (response.ok) {
        const data = await response.json();
        await fetch(`http://localhost:8081/recordlabels/1/enroll-artist/${data.id}`, {
          method: "PUT",
        });
        window.location.reload();
      }
    } catch (error) {
      console.error("Error:", error);
    }
  }

  async function createAlbum() {
    const data = {
      title: albumFormData.title,
      artistId: albumFormData.artistId,
      recordlabelId: 1,
      songsList: albumFormData.songs.map((s) => ({
        title: s.title,
        duration: parseInt(s.duration) || 0,
      })),
    };
    const res = await fetch("http://localhost:8083/albums", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    if (res.ok) window.location.reload();
  }

  async function createEp() {
    const data = {
      title: epFormData.title,
      artistId: albumFormData.artistId, // Återanvänder artistId från samma selector
      recordlabelId: 1,
      songsList: epFormData.songs.map((s) => ({
        title: s.title,
        duration: parseInt(s.duration) || 0,
      })),
    };
    const res = await fetch("http://localhost:8083/eps", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    if (res.ok) window.location.reload();
  }

  async function createSingle() {
    const data = {
      title: releaseFormData.title,
      duration: parseInt(releaseFormData.duration) || 0,
      artistId: albumFormData.artistId, 
      recordlabelId: 1,
    };
    const res = await fetch("http://localhost:8083/singles", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    if (res.ok) window.location.reload();
  }

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    if (type === "artist") await createAndEnrollArtist();
    else if (type === "single") await createSingle();
    else if (type === "album") await createAlbum();
    else if (type.toLowerCase() === "ep") await createEp();
    handleClose();
  };

  return (
    <Modal open={open} onClose={handleClose} closeAfterTransition slots={{ backdrop: Backdrop }}>
      <Fade in={open}>
        <Box sx={style}>
          <Typography variant="h6" sx={{ color: "#9e79a1ff", mb: 2 }}>
            Create {type.toUpperCase()}
          </Typography>
          <Box component="form" onSubmit={handleSubmit} sx={{ display: "flex", flexDirection: "column", gap: 1 }}>
            
            {type === "artist" && (
              <TextField placeholder="Artist Name" value={artistFormData.name} onChange={(e) => setArtistFormData({ name: e.target.value })} fullWidth sx={inputStyle} />
            )}

            {type === "single" && (
              <>
                <TextField placeholder="Single Title" value={releaseFormData.title} onChange={(e) => setReleaseFormData({ ...releaseFormData, title: e.target.value })} fullWidth sx={inputStyle} />
                <TextField placeholder="Duration (seconds)" value={releaseFormData.duration} onChange={(e) => setReleaseFormData({ ...releaseFormData, duration: e.target.value })} fullWidth sx={inputStyle} />
              </>
            )}

            {type.toLowerCase() === "ep" && (
              <>
                <TextField placeholder="EP Title" value={epFormData.title} onChange={(e) => setEpFormData({ ...epFormData, title: e.target.value })} fullWidth sx={inputStyle} />
                {epFormData.songs.map((song, i) => (
                  <Box key={i} sx={{ display: "flex", gap: 1 }}>
                    <TextField placeholder={`Song ${i + 1} Title`} value={song.title} onChange={(e) => {
                      const newSongs = [...epFormData.songs];
                      newSongs[i].title = e.target.value;
                      setEpFormData({ ...epFormData, songs: newSongs });
                    }} fullWidth sx={inputStyle} />
                    <TextField placeholder="Sec" value={song.duration} onChange={(e) => {
                      const newSongs = [...epFormData.songs];
                      newSongs[i].duration = e.target.value;
                      setEpFormData({ ...epFormData, songs: newSongs });
                    }} sx={{ ...inputStyle, width: "100px" }} />
                  </Box>
                ))}
              </>
            )}

            {type === "album" && (
              <>
                <TextField placeholder="Album Title" value={albumFormData.title} onChange={(e) => setAlbumFormData({ ...albumFormData, title: e.target.value })} fullWidth sx={inputStyle} />
                {albumFormData.songs.map((song, i) => (
                  <Box key={i} sx={{ display: "flex", gap: 1 }}>
                    <TextField placeholder={`Song ${i + 1} Title`} value={song.title} onChange={(e) => {
                      const newSongs = [...albumFormData.songs];
                      newSongs[i].title = e.target.value;
                      setAlbumFormData({ ...albumFormData, songs: newSongs });
                    }} fullWidth sx={inputStyle} />
                    <TextField placeholder="Sec" value={song.duration} onChange={(e) => {
                      const newSongs = [...albumFormData.songs];
                      newSongs[i].duration = e.target.value;
                      setAlbumFormData({ ...albumFormData, songs: newSongs });
                    }} sx={{ ...inputStyle, width: "100px" }} />
                  </Box>
                ))}
              </>
            )}

            <Box sx={{ display: "flex", justifyContent: "space-between", mt: 2 }}>
              {type !== "artist" && (
                <FormControl variant="filled" size="small" sx={{ width: 200, background: "#d7d7d718", borderRadius: 2 }}>
                  <InputLabel sx={{ color: "#8e7990e4" }}>Select Artist</InputLabel>
                  <Select value={albumFormData.artistId} onChange={(e) => setAlbumFormData({ ...albumFormData, artistId: e.target.value as string })} sx={{ color: "#aea5afff" }}>
                    {artists.map((a) => (
                      <MenuItem key={a.id} value={a.id}>{a.name}</MenuItem>
                    ))}
                  </Select>
                </FormControl>
              )}
              <Button type="submit" variant="contained" sx={{ bgcolor: "#8F6D92", "&:hover": { bgcolor: "#7a5c7d" } }}>
                Create
              </Button>
            </Box>
          </Box>
        </Box>
      </Fade>
    </Modal>
  );
}

export default ModalMenu;