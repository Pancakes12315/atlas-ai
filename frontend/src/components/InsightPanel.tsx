import InsightCard from "./InsightCard";
import insights from "../data/insights";


function InsightPanel() {


  return (

    <section className="insight-panel">


      <div className="insight-header">


        <p>
          ATLAS AI INSIGHTS
        </p>


        <h2>
          Intelligence beyond search.
        </h2>


      </div>



      <div className="insight-grid">


        {insights.map((insight) => (

          <InsightCard

            key={insight.title}

            {...insight}

          />

        ))}


      </div>



    </section>

  );

}



export default InsightPanel;