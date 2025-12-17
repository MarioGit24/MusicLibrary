import AddIcon from "@mui/icons-material/Add";
import AlbumIcon from "@mui/icons-material/Album";
import AlbumOutlinedIcon from "@mui/icons-material/AlbumOutlined";
import LibraryMusicOutlinedIcon from "@mui/icons-material/LibraryMusicOutlined";
import MusicNoteOutlinedIcon from "@mui/icons-material/MusicNoteOutlined";
import PersonOutlineOutlinedIcon from "@mui/icons-material/PersonOutlineOutlined";
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

  const menuData = [
    { name: "artist", icon: "" },
    { name: "album", icon: "" },
    { name: "EP", icon: "" },
    { name: "single", icon: "" },
  ];

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
        {menuData.map((item) => (
          <MenuItem onClick={() => handleOpenModal(item.name)}>
            <div
              style={{
                padding: 6,
                marginRight: 10,
                borderRadius: "50%",
                backgroundColor: "#ffffff10",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
              }}
            >
              {item.name === "album" ? (
                <AlbumIcon
                  sx={{
                    opacity: 0.8,
                    width: 20,
                    height: 20,
                  }}
                />
              ) : item.name === "EP" ? (
                <AlbumOutlinedIcon
                  sx={{
                    opacity: 0.8,
                    width: 20,
                    height: 20,
                  }}
                />
              ) : item.name === "artist" ? (
                <PersonOutlineOutlinedIcon
                  sx={{
                    opacity: 0.8,
                    width: 20,
                    height: 20,
                  }}
                />
              ) : item.name === "song" ? (
                <LibraryMusicOutlinedIcon
                  sx={{
                    opacity: 0.8,
                    width: 20,
                    height: 20,
                  }}
                />
              ) : item.name === "single" ? (
                <MusicNoteOutlinedIcon
                  sx={{
                    opacity: 0.8,
                    width: 20,
                    height: 20,
                  }}
                />
              ) : (
                ""
              )}
            </div>
            <p style={{ fontSize: 14 }}>Add {item.name}</p>
          </MenuItem>
        ))}
      </Menu>
      <ModalMenu open={modalOpen} setOpen={setModalOpen} type={addType} />
    </div>
  );
}

export default PopoverMenu;
