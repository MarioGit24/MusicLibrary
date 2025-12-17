import { useEffect, useState } from "react";
import { RecordlabelData } from "./types/ApiTypes";

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
      <h1>{labelData.labelName}</h1>

      <h2>Artists</h2>
      <ul>
        {labelData.artists.map((artist) => (
          <li key={artist.id}>{artist.name}</li>
        ))}
      </ul>

      <h2>Releases</h2>
      <ul>
        {labelData.releases.map((release) => (
          <li key={release.id}>
            {release.title} ({release.type})
            <ul>
              {release.songs.map((song) => (
                <li key={song.title}>{song.title}</li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RecordLabelDashboard;
