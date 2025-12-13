import Backdrop from "@mui/material/Backdrop";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Fade from "@mui/material/Fade";
import Modal from "@mui/material/Modal";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import { useState } from "react";

const style = {
  position: "absolute",
  top: "45%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "80%",
  bgcolor: "#1f1f1f",
  borderRadius: "5px",
  boxShadow: 10,
  p: 3,
};

interface Props {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<any>>;
  type: string;
}

function ModalMenu({ open, setOpen, type }: Props) {
  const handleClose = () => setOpen(false);
  const [formData, setFormData] = useState({
    name: "",
  });

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log(formData);
    // Add form submission logic here
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
            <Typography
              id="transition-modal-description"
              sx={{ mt: 1, fontSize: 13 }}
            >
              {type === "album"
                ? "Create an album with songs"
                : type === "EP"
                ? "Create an EP with songs"
                : ""}
            </Typography>
            <Box
              component="form"
              onSubmit={handleSubmit}
              sx={{ display: "flex", flexDirection: "column", gap: 2 }}
            >
              <TextField
                placeholder="Add a name..."
                name="name"
                value={formData.name}
                onChange={handleChange}
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
              <div
                style={{ display: "flex", justifyContent: "end", marginTop: 5 }}
              >
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
