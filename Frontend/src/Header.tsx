import SearchIcon from "@mui/icons-material/Search";
import Autocomplete from "@mui/material/Autocomplete";
import InputAdornment from "@mui/material/InputAdornment";
import TextField from "@mui/material/TextField";

function Header() {
  return (
    <header
      style={{
        paddingTop: 15,
        paddingLeft: 25,
        paddingRight: 20,
        height: "100vh",
        backgroundColor: "#121212",
        cursor: "default",
        display: "flex",
      }}
    >
      <h2 style={{ fontWeight: 500, letterSpacing: -1 }}>Spotify</h2>

      <Autocomplete
        disablePortal
        options={["Test", "Test2"]}
        sx={{
          width: 350,
          marginX: "auto",
          height: 0,
          "& .MuiOutlinedInput-root": {
            paddingLeft: "8px",
            paddingBottom: 0.3,
            paddingTop: 0.3,
            borderRadius: "999px",
            backgroundColor: "#1f1f1f",
            color: "#fff",
            "& fieldset": {
              border: "none",
            },

            "&:hover": {
              backgroundColor: "#2a2a2a",
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
                  "&:hover": {
                    backgroundColor: "#222222ff",
                  },
                },
              },
            },
          },
        }}
        renderInput={(params) => (
          <TextField
            {...params}
            placeholder="Search..."
            InputProps={{
              ...params.InputProps,
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon sx={{ color: "#b3b3b3", marginLeft: "4px" }} />
                </InputAdornment>
              ),
            }}
          />
        )}
      />
    </header>
  );
}

export default Header;
