import RecordLabelDashboard from "./dashboard/RecordLabelDashboard";
import Header from "./Header";
import PopoverMenu from "./menu/PopopverMenu";

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
      <div
        style={{
          backgroundColor: "#121212",
        }}
      >
        <PopoverMenu />
        {/* TODO: take back <Artists /> */}
        <RecordLabelDashboard />
      </div>
    </div>
  );
}

export default App;
