export interface ArtistData {
  id: number;
  name: string;
}

export interface SongData {
  id: number;
  title: string;
  duration: number;
}

export interface ReleasesData {
  id: number;
  title: string;
  type: string;
  songs: SongData[];
}

export interface RecordlabelData {
  labelName: string;
  artists: ArtistData[];
  releases: ReleasesData[];
}
