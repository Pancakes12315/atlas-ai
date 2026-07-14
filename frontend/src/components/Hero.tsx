type HeroButton = {
  label: string;
  variant: "primary" | "secondary";
};


const heroContent = {
  tagline: "AI-NATIVE ENTERPRISE SEARCH",

  title:
    "Find knowledge faster with intelligent AI search.",

  description:
    "Atlas AI connects your organization's knowledge, understands context, and delivers explainable answers before you even ask.",
};


const heroButtons: HeroButton[] = [
  {
    label: "Get Started",
    variant: "primary",
  },
  {
    label: "View Demo",
    variant: "secondary",
  },
];


function Hero() {
  return (
    <section className="hero">

      <p className="hero-tagline">
        {heroContent.tagline}
      </p>


      <h1 className="hero-title">
        {heroContent.title}
      </h1>


      <p className="hero-description">
        {heroContent.description}
      </p>


      <div className="hero-buttons">

        {heroButtons.map((button) => (
          <button
            key={button.label}
            className={`hero-button ${button.variant}`}
          >
            {button.label}
          </button>
        ))}

      </div>


    </section>
  );
}


export default Hero;