import { useState } from "react";


type Source = {
  name: string;
  section: string;
  updated: string;
  relevance: number;
};


type SearchResult = {
  answer: string;
  confidence: number;
  sources: Source[];
  reasoning: string[];
  recommendations: string[];
};



const defaultResult: SearchResult = {

  answer:
    "The Q3 marketing strategy focuses on improving customer acquisition, expanding enterprise partnerships, and increasing product adoption.",

  confidence: 94,

  sources: [

    {
      name: "Marketing Strategy Q3.pdf",
      section: "Campaign Strategy",
      updated: "3 days ago",
      relevance: 98,
    },

    {
      name: "Growth Plan 2026.docx",
      section: "Enterprise Expansion",
      updated: "1 week ago",
      relevance: 94,
    },

    {
      name: "Team Meeting Notes",
      section: "Product Growth Planning",
      updated: "Today",
      relevance: 90,
    },

  ],


  reasoning: [

    "Matched relevant marketing documents",

    "Compared related project discussions",

    "Identified similar previous campaigns",

  ],


  recommendations: [

    "Review competitor analysis",

    "Open campaign performance dashboard",

    "Analyze customer feedback trends",

  ],

};



const thinkingSteps = [

  "Understanding your request",

  "Searching organizational knowledge",

  "Ranking relevant documents",

  "Generating AI response",

];




function SearchDemo() {


  const [query, setQuery] = useState(
    "Where is our Q3 marketing strategy?"
  );


  const [result, setResult] =
    useState<SearchResult>(defaultResult);


  const [loading, setLoading] =
    useState(false);


  const [thinkingStep, setThinkingStep] =
    useState(-1);




  const handleSearch = async () => {


    setLoading(true);

    setThinkingStep(0);



    const timers = [

      setTimeout(() => {

        setThinkingStep(1);

      }, 700),



      setTimeout(() => {

        setThinkingStep(2);

      }, 1400),



      setTimeout(() => {

        setThinkingStep(3);

      }, 2100),

    ];




    try {


      const response = await fetch(

        `http://localhost:8080/api/search?query=${encodeURIComponent(query)}`

      );



      if (!response.ok) {

        throw new Error(
          "Search request failed"
        );

      }



      const data: SearchResult =
        await response.json();



      await new Promise(resolve =>
        setTimeout(resolve, 1500)
      );



      setResult(data);



    } catch (error) {


      console.error(
        "Search failed:",
        error
      );



    } finally {


      timers.forEach(clearTimeout);


      setLoading(false);


      setThinkingStep(-1);


    }


  };




  const confidenceLabel =

    result.confidence >= 90

      ? "High Confidence"

      : result.confidence >= 75

      ? "Medium Confidence"

      : "Low Confidence";






  return (

    <section className="search-demo">



      <div className="search-header">


        <p>
          ASK ATLAS AI
        </p>


        <h2>
          Search your organization's knowledge instantly.
        </h2>


      </div>





      <div className="search-box">



        <input

          value={query}

          onChange={(event) =>
            setQuery(event.target.value)
          }

          placeholder="Ask Atlas AI..."

        />



        <button onClick={handleSearch}>

          Search

        </button>



      </div>





      <div className="answer-card">



        {loading ? (



          <div className="ai-thinking">



            <h3>

              ✨ Atlas AI is thinking

            </h3>




            <div className="thinking-steps">



              {thinkingSteps.map((step, index) => (



                <div

                  key={step}

                  className={

                    index <= thinkingStep

                      ? "thinking-step active"

                      : "thinking-step"

                  }

                >



                  <span>

                    {index <= thinkingStep

                      ? "✓"

                      : "○"}

                  </span>



                  {step}



                </div>



              ))}



            </div>



          </div>




        ) : (



          <>



            <h3>

              Atlas AI Answer

            </h3>




            <p>

              {result.answer}

            </p>






            <div className="confidence">



              <h4>

                Answer Confidence

              </h4>




              <div className="confidence-bar">



                <div

                  className="confidence-fill"

                  style={{

                    width:
                      `${result.confidence}%`,

                  }}

                />



              </div>





              <div className="confidence-info">



                <div className="confidence-score">

                  {result.confidence}%

                </div>



                <div className="confidence-level">

                  {confidenceLabel}

                </div>



              </div>



            </div>








            <div className="sources">



              <h4>

                Sources Used

              </h4>





              {result.sources.map((source) => (



                <div

                  key={source.name}

                  className="source-card"

                >



                  <h5>

                    📄 {source.name}

                  </h5>



                  <p>

                    {source.section}

                  </p>




                  <div className="source-meta">



                    <span>

                      Updated {source.updated}

                    </span>




                    <span>

                      {source.relevance}% relevance

                    </span>



                  </div>



                </div>



              ))}



            </div>








            <div className="reasoning">



              <h4>

                Why Atlas AI recommended this answer

              </h4>




              {result.reasoning.map((item) => (



                <div key={item}>

                  ✓ {item}

                </div>



              ))}



            </div>








            <div className="recommendations">



              <h4>

                Recommended Actions

              </h4>





              {result.recommendations.map((item) => (



                <div key={item}>


                  →

                  {" "}

                  {item}



                </div>



              ))}



            </div>



          </>



        )}



      </div>



    </section>

  );

}



export default SearchDemo;