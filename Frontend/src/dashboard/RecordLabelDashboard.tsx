import { useEffect, useState } from "react";
import { RecordlabelData } from "../types/ApiTypes";
import AlbumItem from "./AlbumItem";
import TypeFilter from "./TypeFilter";

const RecordLabelDashboard = () => {
  const [labelData, setLabelData] = useState<RecordlabelData>();
  const [type, setType] = useState("");

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
          <div
            style={{
              marginTop: 40,
              marginBottom: 30,
              display: "flex",
              gap: 40,
            }}
          >
            <h2>Releases</h2>
            <TypeFilter setType={setType} />
          </div>
          <div style={{ display: "flex", gap: 12 }}>
            {labelData.releases.map((release, i) => (
              <div key={i}>
                <AlbumItem release={release} filterType={type} />
              </div>
            ))}
          </div>
        </>
      )}
    </div>
  );
};

export default RecordLabelDashboard;
