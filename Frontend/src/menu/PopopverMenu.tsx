import AddIcon from "@mui/icons-material/Add";
import IconButton from "@mui/material/IconButton";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import { useState } from "react";
import ModalMenu from "./Modal";

function PopoverMenu() {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const [modalOpen, setModalOpen] = useState(false);
  const [addType, setAddType] = useState("");
  const open = Boolean(anchorEl);

  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleOpenModal = (type: string) => {
    setAnchorEl(null);
    setModalOpen(true);
    setAddType(type);
  };

  return (
    <div>
      <IconButton
        id="demo-positioned-button"
        aria-controls={open ? "demo-positioned-menu" : undefined}
        aria-haspopup="true"
        aria-expanded={open ? "true" : undefined}
        onClick={handleClick}
      >
        <AddIcon sx={{ fill: "#8F6D92" }} />
      </IconButton>
      <Menu
        aria-labelledby="demo-positioned-button"
        anchorEl={anchorEl}
        open={open}
        onClose={handleOpenModal}
        anchorOrigin={{
          vertical: "top",
          horizontal: "left",
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "left",
        }}
        sx={{
          marginX: "auto",
          "& .MuiOutlinedInput-root": {
            paddingBottom: 0.3,
            paddingTop: 0.3,
            borderRadius: "999px",
            backgroundColor: "#1f1f1f",
            color: "#fff",
            "& fieldset": {
              border: "none",
            },
            "&:hover": {
              backgroundColor: "#404040ff",
            },
            "&.Mui-focused": {
              backgroundColor: "#2a2a2a",
              boxShadow: "0 0 0 2px #535353",
            },
          },
          "& .MuiInputLabel-root": {
            color: "#b3b3b3",
          },
          "& .MuiInputBase-input": {
            paddingLeft: "0px !important",
          },
        }}
        slotProps={{
          paper: {
            sx: {
              "& ul > li:first-of-type": {
                borderTopLeftRadius: "10px",
                borderTopRightRadius: "10px",
              },
              "& ul > li:last-child": {
                borderBottomLeftRadius: "10px",
                borderBottomRightRadius: "10px",
              },
              backgroundColor: "#00000000 !important",
              boxShadow: "none",
              "& .MuiAutocomplete-listbox": {
                marginTop: 0.1,
                backgroundColor: "#00000000 !important",
                "& .MuiAutocomplete-option": {
                  backgroundColor: "#1f1f1f",
                },
              },
            },
          },
        }}
      >
        <MenuItem onClick={() => handleOpenModal("artist")}>
          Add artist
        </MenuItem>
        <MenuItem onClick={() => handleOpenModal("album")}>Add album</MenuItem>
        <MenuItem onClick={() => handleOpenModal("EP")}>Add EP</MenuItem>
        <MenuItem onClick={() => handleOpenModal("single")}>
          Add single
        </MenuItem>
        <MenuItem onClick={() => handleOpenModal("song")}>Add song</MenuItem>
      </Menu>
      <ModalMenu open={modalOpen} setOpen={setModalOpen} type={addType} />
    </div>
  );
}

export default PopoverMenu;
