import { useEffect, useState } from "react";
import { RecordlabelData } from "../types/ApiTypes";
import AlbumItem from "./AlbumItem";

const RecordLabelDashboard = () => {
  const [labelData, setLabelData] = useState<RecordlabelData>();

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
      {labelData.artists.length !== 0 && (
        <>
          <h2 style={{ marginTop: 30, marginBottom: 10 }}>Artists</h2>
          <ul>
            {labelData.artists.map((artist) => (
              <p key={artist.id}>{artist.name}</p>
            ))}
          </ul>
        </>
      )}

      {labelData.releases.length !== 0 && (
        <>
          <h2 style={{ marginTop: 30, marginBottom: 10 }}>Releases</h2>
          <ul>
            {labelData.releases.map((release) => (
              <AlbumItem release={release} />
            ))}
          </ul>
        </>
      )}
    </div>
  );
};

export default RecordLabelDashboard;
