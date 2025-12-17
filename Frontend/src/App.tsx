import Header from "./Header";
import PopoverMenu from "./menu/PopopverMenu";
import RecordLabelDashboard from "./RecordLabelDashboard";

function App() {
  return (
    <div
      style={{
        height: "100vh",
        backgroundColor: "#121212",
        paddingLeft: 25,
        paddingRight: 25,
      }}
    >
      <Header />
      <PopoverMenu />
      {/* TODO: take back <Artists /> */}
      <RecordLabelDashboard />
    </div>
  );
}

export default App;
