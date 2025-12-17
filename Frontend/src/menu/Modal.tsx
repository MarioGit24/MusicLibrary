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

interface Props {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<any>>;
  type: string;
}

function ModalMenu({ open, setOpen, type }: Props) {
  const handleClose = () => setOpen(false);

  const [artists, setArtists] = useState<{ id: number; name: string }[]>([]);

  const [artistFormData, setArtistFormData] = useState({
    name: "",
  });

  const [releaseFormData, setReleaseFormData] = useState({
    title: "",
    duration: 0,
  });

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

  const handleChangeArtist = (e: any) => {
    const { name, value } = e.target;
    setArtistFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleChangeSingle = (e: any) => {
    const { name, value } = e.target;
    setReleaseFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleAlbumChange = (e: any) => {
    const { name, value } = e.target;
    setAlbumFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };
  const handleEpChange = (e: any) => {
    const { name, value } = e.target;
    setEpFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleAlbumSongChange = (index: number, field: string, value: string) => {
    const updatedSongs = [...albumFormData.songs];
    updatedSongs[index] = { ...updatedSongs[index], [field]: value };
    setAlbumFormData((prevState) => ({
      ...prevState,
      songs: updatedSongs,
    }));
  };

  const handleEpSongChange = (index: number, field: string, value: string) => {
    const updatedSongs = [...epFormData.songs];
    updatedSongs[index] = { ...updatedSongs[index], [field]: value };
    setEpFormData((prevState) => ({
      ...prevState,
      songs: updatedSongs,
    }));
  };

  useEffect(() => {
    const fetchArtists = async () => {
      try {
        const response = await fetch("http://localhost:8082/artists");
        const data = await response.json();
        setArtists(data);
      } catch (error) {
        console.error("Error fetching dashboard:", error);
      }
    };

    fetchArtists();
  }, []);

  async function createAndEnrollArtist() {
    let artistId;
    try {
      const response = await fetch("http://localhost:8082/artists", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          name: artistFormData.name,
        }),
      });

      if (response.ok) {
        const data = await response.json();
        artistId = data.id;
        console.log("Artist created with ID:", data.id);
      }
    } catch (error) {
      console.error("Error creating artist:", error);
    }
    try {
      const response = await fetch(
        `http://localhost:8081/recordlabels/1/enroll-artist/${artistId}`,
        // hardcoded record label id since we only will have one record label
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
        }
      );

      if (response.ok) {
        const text = await response.text();
        console.log(text);
      }
    } catch (error) {
      console.error("Error enrolling artist:", error);
    }
  }

  async function createAlbum() {
    const albumData = {
      title: albumFormData.title,
      artistId: albumFormData.artistId,
      recordlabelId: 1, // hardcoded label
      songsList: albumFormData.songs.map((song) => ({
        title: song.title,
        duration: parseInt(song.duration) || 0,
      })),
    };   

    try {
      const response = await fetch("http://localhost:8083/albums", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(albumData),
      });

      if (response.ok) {
        console.log("Album created successfully");
      }
    } catch (error) {
      console.error("Error creating album:", error);
    }
  }

  async function createEp() {
    const epData = {
      title: epFormData.title,
      artistId: epFormData.artistId,
      recordlabelId: 1, // hardcoded label
      songsList: epFormData.songs.map((song) => ({
        title: song.title,
        duration: parseInt(song.duration) || 0,
      })),
    };
        try {
      const response = await fetch("http://localhost:8083/eps", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(epData),
      });

      if (response.ok) {
        console.log("EP created successfully");
      }
    } catch (error) {
      console.error("Error creating EP:", error);
    }

  }

  async function createSingle() {
    console.log("Single");
  }

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    if (type === "artist") {
      createAndEnrollArtist();
    } else if (type === "single") {
      createSingle();
    } else if (type === "album") {
      createAlbum();
    } else if (type == "EP") {
      createEp(); 
    }
    handleClose();
  };

  return (
    <div>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleClose}
        closeAfterTransition
        slots={{ backdrop: Backdrop }}
        slotProps={{
          backdrop: {
            timeout: 500,
          },
        }}
      >
        <Fade in={open}>
          <Box sx={style}>
            <Typography
              id="transition-modal-title"
              variant="h6"
              component="h2"
              style={{ color: "#9e79a1ff" }}
            >
              Create {type}
            </Typography>
            <Box
              component="form"
              onSubmit={handleSubmit}
              sx={{
                gap: 2,
                flex: 1,
                overflowY: "auto",
                display: "flex",
                flexDirection: "column",
                justifyContent: "space-between",
              }}
            >
              {type === "artist" ? (
                <TextField
                  placeholder="Add a name..."
                  name="name"
                  value={artistFormData.name}
                  onChange={handleChangeArtist}
                  fullWidth
                  sx={{
                    mt: 2,
                    background: "#d7d7d718",
                    borderRadius: 2,
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
                  }}
                />
              ) : type === "single" ? (
                <>
                  <TextField
                    placeholder="Add a title..."
                    name="title"
                    value={releaseFormData.title}
                    onChange={handleChangeSingle}
                    fullWidth
                    sx={{
                      mt: 2,
                      background: "#d7d7d718",
                      borderRadius: 2,
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
                    }}
                  />
                  <TextField
                    placeholder="Add a duration..."
                    name="duration"
                    value={releaseFormData.duration}
                    onChange={handleChangeSingle}
                    fullWidth
                    sx={{
                      mt: 2,
                      background: "#d7d7d718",
                      borderRadius: 2,
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
                    }}
                  />
                </>
              ) : type === "ep" ? (
                <>
                  {/* ALBUM SECTION START */}
                  <TextField
                    placeholder="Add an album title..."
                    name="title"
                    value={albumFormData.title}
                    onChange={handleAlbumChange}
                    fullWidth
                    sx={{
                      mt: 2,
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
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
                    }}
                  />

                  {/* SONG 1 */}
                  <p>Song 1:</p>
                  <TextField
                    placeholder="Add a song title..."
                    value={albumFormData.songs[0].title}
                    onChange={(e) =>
                      handleAlbumSongChange(0, "title", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />
                  <TextField
                    placeholder="Add a song duration..."
                    value={albumFormData.songs[0].duration}
                    onChange={(e) =>
                      handleAlbumSongChange(0, "duration", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />

                  {/* SONG 2 */}
                  <p>Song 2:</p>
                  <TextField
                    placeholder="Add a song title..."
                    value={albumFormData.songs[1].title}
                    onChange={(e) =>
                      handleAlbumSongChange(1, "title", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />
                  <TextField
                    placeholder="Add a song duration..."
                    value={albumFormData.songs[1].duration}
                    onChange={(e) =>
                      handleAlbumSongChange(1, "duration", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />

                  {/* SONG 3 */}
                  <p>Song 3:</p>
                  <TextField
                    placeholder="Add a song title..."
                    value={albumFormData.songs[2].title}
                    onChange={(e) =>
                      handleAlbumSongChange(2, "title", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />
                  <TextField
                    placeholder="Add a song duration..."
                    value={albumFormData.songs[2].duration}
                    onChange={(e) =>
                      handleAlbumSongChange(2, "duration", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />
                </>
              ) : (
                <>
                  {/* ALBUM SECTION START */}
                  <TextField
                    placeholder="Add an album title..."
                    name="title"
                    value={albumFormData.title}
                    onChange={handleAlbumChange}
                    fullWidth
                    sx={{
                      mt: 2,
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
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
                    }}
                  />

                  {/* SONG 1 */}
                  <p>Song 1:</p>
                  <TextField
                    placeholder="Add a song title..."
                    value={albumFormData.songs[0].title}
                    onChange={(e) =>
                      handleAlbumSongChange(0, "title", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />
                  <TextField
                    placeholder="Add a song duration..."
                    value={albumFormData.songs[0].duration}
                    onChange={(e) =>
                      handleAlbumSongChange(0, "duration", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />

                  {/* SONG 2 */}
                  <p>Song 2:</p>
                  <TextField
                    placeholder="Add a song title..."
                    value={albumFormData.songs[1].title}
                    onChange={(e) =>
                      handleAlbumSongChange(1, "title", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />
                  <TextField
                    placeholder="Add a song duration..."
                    value={albumFormData.songs[1].duration}
                    onChange={(e) =>
                      handleAlbumSongChange(1, "duration", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />

                  {/* SONG 3 */}
                  <p>Song 3:</p>
                  <TextField
                    placeholder="Add a song title..."
                    value={albumFormData.songs[2].title}
                    onChange={(e) =>
                      handleAlbumSongChange(2, "title", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />
                  <TextField
                    placeholder="Add a song duration..."
                    value={albumFormData.songs[2].duration}
                    onChange={(e) =>
                      handleAlbumSongChange(2, "duration", e.target.value)
                    }
                    fullWidth
                    sx={{
                      background: "#d7d7d718",
                      borderRadius: 2,
                      input: {
                        color: "#aea5afff",
                        padding: 1.5,
                        "&::placeholder": { color: "#8e7990e4" },
                      },
                      fieldset: { borderColor: "#aea5af23" },
                      "& .MuiOutlinedInput-root": {
                        "&.Mui-focused fieldset": { borderColor: "#aea5af23" },
                      },
                    }}
                  />
                </>
              )}
              <div
                style={{
                  display: "flex",
                  marginTop: 6,
                  justifyContent: "space-between",
                }}
              >
                {type === "album" && (
                  <FormControl
                    variant="filled"
                    size="small"
                    sx={{
                      width: 110,
                      background: "#d7d7d718",
                      borderRadius: 2,
                      "& .MuiSelect-select": {
                        color: "#aea5afff",
                      },
                      "& .MuiFilledInput-root": {
                        "&:before": {
                          borderBottomColor: "#aea5af23",
                        },

                        "&:hover:not(.Mui-disabled):before": {
                          borderBottomColor: "#aea5afff",
                        },
                        "&:after": {
                          borderBottomColor: "#aea5afff",
                        },
                      },
                      "& .MuiInputLabel-root": {
                        color: "#8e7990e4",
                        "&.Mui-focused": {
                          color: "#aea5afff",
                        },
                      },
                    }}
                  >
                    <InputLabel>Artist</InputLabel>
                    <Select
                      name="artistId"
                      value={albumFormData.artistId}
                      onChange={handleAlbumChange}
                    >
                      {artists.map((artist) => (
                        <MenuItem key={artist.id} value={artist.id}>
                          {artist.name}
                        </MenuItem>
                      ))}
                    </Select>
                  </FormControl>
                )}
                <Button
                  type="submit"
                  style={{
                    color: "#8F6D92",
                    background: "#16161693",
                  }}
                >
                  Create
                </Button>
              </div>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}

export default ModalMenu;
