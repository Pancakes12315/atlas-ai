import { useState } from "react";


const defaultResult = {

  answer:
    "The Q3 marketing strategy focuses on improving customer acquisition, expanding enterprise partnerships, and increasing product adoption.",


  confidence: 94,


  sources: [

    {
      name: "Marketing Strategy Q3.pdf",
      detail: "Section: Campaign Strategy • Updated 3 days ago",
    },

    {
      name: "Growth Plan 2026.docx",
      detail: "Section: Enterprise Expansion Goals",
    },

    {
      name: "Team Meeting Notes",
      detail: "Discussion: Product Growth Planning",
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



const analysisSteps = [

  "Searching company documents",

  "Comparing related knowledge",

  "Generating contextual answer",

];



function SearchDemo() {


  const [query, setQuery] = useState(
    "Where is our Q3 marketing strategy?"
  );


  const [result, setResult] = useState(defaultResult);


  const [loading, setLoading] = useState(false);


  const [step, setStep] = useState(0);



  const handleSearch = () => {


    setLoading(true);

    setStep(0);



    const interval = setInterval(() => {


      setStep((current) => {


        if (current < analysisSteps.length - 1) {

          return current + 1;

        }


        return current;

      });


    }, 400);



    setTimeout(() => {


      clearInterval(interval);



      setResult({

        answer:
          `Atlas AI found information related to "${query}". The system analyzed company documents and identified relevant knowledge sources.`,


        confidence: 91,


        sources: [

          {
            name: "Company Knowledge Base",
            detail: "Multiple matching documents found",
          },

          {
            name: "Project Documentation",
            detail: "Related team discussions identified",
          },

        ],


        reasoning: [

          "Analyzed semantic similarity between documents",

          "Found related organizational knowledge",

          "Ranked the most relevant information",

        ],


        recommendations: [

          "Review related documents",

          "Explore similar projects",

          "Ask a follow-up question",

        ],

      });



      setLoading(false);


    }, 1800);


  };



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



        <button

          onClick={handleSearch}

          disabled={loading}

        >

          {loading ? "Searching..." : "Search"}

        </button>


      </div>




      <div className="answer-card">


        {loading ? (


          <>

            <h3>
              Atlas AI is analyzing your knowledge base...
            </h3>



            <div className="analysis-steps">


              {analysisSteps.map((item, index) => (

                <p key={item}>

                  {index <= step ? "✓" : "○"}

                  {" "}

                  {item}

                </p>

              ))}


            </div>


          </>


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


              <p>
                {result.confidence}%
              </p>


            </div>





            <div className="sources">


              <h4>
                Sources Used
              </h4>



              {result.sources.map((source) => (

                <div key={source.name}>

                  📄 <strong>{source.name}</strong>

                  <br />

                  <span>
                    {source.detail}
                  </span>

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