import { useState } from "react";
import markurl from "../assets/questionmark.svg";
import { ReleasesData } from "../types/ApiTypes";
import SongsDetails from "./SongsDetails";

interface AlbumItemProps {
  release: ReleasesData;
  filterType: string;
}

const AlbumItem = ({ release, filterType }: AlbumItemProps) => {
  const { id, title, type, songs } = release;

  if (filterType && filterType.toLowerCase() !== type.toLowerCase()) {
    return null;
  }

  // eslint-disable-next-line react-hooks/rules-of-hooks
  const [modalOpen, setModalOpen] = useState(false);

  const handleOpenModal = () => {
    setModalOpen(true);
  };

  return (
    <>
      <div
        key={id}
        style={{
          cursor: "pointer",
        }}
        onClick={handleOpenModal}
      >
        <div
          style={{
            width: 150,
            height: 150,
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            backgroundColor: "#ffffff0c",
          }}
        >
          <img
            src={markurl}
            alt=""
            style={{
              width: 100,
              opacity: 0.4,
            }}
          />
        </div>
        <p
          style={{
            marginTop: 4,
            marginLeft: 2,
            fontSize: 16,
            maxWidth: 150,
            overflow: "hidden",
            textOverflow: "ellipsis",
            display: "inline-block",
            whiteSpace: "nowrap",
          }}
        >
          {title}
        </p>
      </div>
      <SongsDetails
        open={modalOpen}
        setOpen={setModalOpen}
        songs={songs}
        albumTitle={title}
        type={type}
      />
    </>
  );
};

export default AlbumItem;
