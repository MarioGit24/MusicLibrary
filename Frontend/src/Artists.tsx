import { useEffect, useState } from "react";

interface Artist {
  id: Number;
  name: String;
}

function Artists() {
  const [artists, setArtists] = useState<Artist[]>([]);

  useEffect(() => {
    fetch("http://localhost:8081/artists")
      .then((res) => res.json())
      .then((artists) => {
        setArtists(artists);
      });
  }, []);

  useEffect(() => {
    console.log(artists);
  }, [artists]);

  return (
    <div>
      {artists.map((artist, i) => (
        <p key={i} style={{ color: "white" }}>
          {artist.name}
        </p>
      ))}
    </div>
  );
}

export default Artists;
