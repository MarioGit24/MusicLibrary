import { useEffect, useState } from "react";
import { RecordlabelData } from "../types/ApiTypes";
import AlbumItem from "./AlbumItem";

const RecordLabelDashboard = () => {
  const [labelData, setLabelData] = useState<RecordlabelData>();
  // 1. Här skapar vi staten för singlar som saknades!
  const [singles, setSingles] = useState<any[]>([]); 

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Hämta skivbolagsdata (Artister + ev. Album)
        const response = await fetch("http://localhost:8081/recordlabels/1");
        const data = await response.json();
        setLabelData(data);

        // Hämta singlar från Release-servicen
        const singlesResponse = await fetch("http://localhost:8083/singles");
        const singlesJson = await singlesResponse.json();
        setSingles(singlesJson); // Nu kommer detta fungera!
        
      } catch (error) {
        console.error("Error fetching dashboard:", error);
      }
    };

    fetchData();
  }, []);

  if (!labelData) return <div style={{ color: "white" }}>Loading dashboard...</div>;

  return (
    <div style={{ color: "white" }}>
      {/* SEKTION FÖR ARTISTER */}
      {labelData.artists && labelData.artists.length !== 0 && (
        <>
          <h2 style={{ marginTop: 30, marginBottom: 10 }}>Artists</h2>
          <div style={{ display: "flex", gap: 10, flexWrap: "wrap" }}>
            {labelData.artists.map((artist) => (
              <p key={artist.id} style={{ background: "#1f1f1f", padding: "5px 15px", borderRadius: "20px" }}>
                {artist.name}
              </p>
            ))}
          </div>
        </>
      )}

      {/* SEKTION FÖR SINGLAR (Här hamnar "Ebba" och "Hej"!) */}
      {singles.length !== 0 && (
        <>
          <h2 style={{ marginTop: 30, marginBottom: 10 }}>Singles (from 8083)</h2>
          <div style={{ display: "flex", flexDirection: "column", gap: 10 }}>
            {singles.map((single, index) => (
              // Vi använder AlbumItem för att visa singeln
              <AlbumItem key={index} release={single} />
            ))}
          </div>
        </>
      )}

      {/* SEKTION FÖR ANDRA RELEASES (Från 8081) */}
      {labelData.releases && labelData.releases.length !== 0 && (
        <>
          <h2 style={{ marginTop: 30, marginBottom: 10 }}>Other Releases</h2>
          <ul>
            {labelData.releases.map((release, index) => (
              <AlbumItem key={index} release={release} />
            ))}
          </ul>
        </>
      )}
    </div>
  );
};

export default RecordLabelDashboard;