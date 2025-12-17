import Backdrop from "@mui/material/Backdrop";
import Box from "@mui/material/Box";
import Fade from "@mui/material/Fade";
import Modal from "@mui/material/Modal";
import Typography from "@mui/material/Typography";
import { SongData } from "../types/ApiTypes";

interface Props {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<any>>;
  // Vi gör songs valfri med ? eftersom Singlar inte har en låtlista
  songs?: SongData[]; 
  albumTitle: string;
}

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "70%",
  minHeight: "50%",
  bgcolor: "#1f1f1f",
  borderRadius: "5px",
  boxShadow: 10,
  p: 3,
  display: "flex",
  flexDirection: "column",
};

function SongsDetails({ open, setOpen, songs, albumTitle }: Props) {
  const handleClose = () => setOpen(false);

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
              {albumTitle}
            </Typography>
            <div
              style={{
                gap: 2,
                flex: 1,
                overflowY: "auto",
                display: "flex",
                flexDirection: "column",
                marginTop: 20,
                marginBottom: 15,
              }}
            >
              {/* FIX: Vi lägger till en kontroll så att map bara körs om songs finns */}
              {songs && songs.length > 0 ? (
                songs.map((song, i) => (
                  <div key={i} style={{ marginBottom: 15 }}>
                    <h4 style={{ color: "#b49bb5e4", marginBottom: 3 }}>
                      Song {i + 1}
                    </h4>
                    <p style={{ margin: 0 }}>
                      <b style={{ color: "#9e79a1" }}>Title:</b> {song.title}
                    </p>
                    <p style={{ margin: 0 }}>
                      <b style={{ color: "#9e79a1" }}>Duration:</b> {song.duration} seconds
                    </p>
                  </div>
                ))
              ) : (
                /* Detta visas om det är en Single utan låtlista */
                <Typography sx={{ color: "#aea5afff", fontStyle: "italic" }}>
                  This is a single release with no additional tracks.
                </Typography>
              )}
            </div>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}

export default SongsDetails;