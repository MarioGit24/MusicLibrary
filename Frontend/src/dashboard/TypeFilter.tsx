import SearchIcon from "@mui/icons-material/Search";
import Autocomplete from "@mui/material/Autocomplete";
import InputAdornment from "@mui/material/InputAdornment";
import TextField from "@mui/material/TextField";
import { useEffect, useState } from "react";
import { RecordlabelData } from "../types/ApiTypes";

interface Props {
  setType: React.Dispatch<React.SetStateAction<any>>;
}

const TypeFilter = ({ setType }: Props) => {
  const [labelData, setLabelData] = useState<RecordlabelData>();
  const menuData = ["Artist", "Album", "EP", "Single"];

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8081/recordlabels/1");
        const data = await response.json();
        setLabelData(data);
      } catch (error) {
        console.error("Error fetching dashboard:", error);
      }
    };

    fetchData();
  }, []);

  if (!labelData) return <div>Loading...</div>;

  return (
    <div>
      <Autocomplete
        disablePortal
        options={menuData}
        onChange={(event: any, newValue: string | null) => {
          setType(newValue || "");
        }}
        sx={{
          width: 250,
          height: 0,
          "& .MuiOutlinedInput-root": {
            paddingLeft: "8px",
            paddingBottom: 0.1,
            paddingTop: 0.1,
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
            placeholder="Filter..."
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
    </div>
  );
};

export default TypeFilter;
