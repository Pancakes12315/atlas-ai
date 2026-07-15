import { motion } from "framer-motion";

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
    <motion.section
      className="hero"
      initial={{ opacity: 0, y: 30 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{
        duration: 0.8,
        ease: "easeOut",
      }}
    >
      <motion.p
        className="hero-tagline"
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{
          delay: 0.2,
        }}
      >
        {heroContent.tagline}
      </motion.p>

      <motion.h1
        className="hero-title"
        initial={{ opacity: 0, y: 15 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{
          delay: 0.3,
          duration: 0.6,
        }}
      >
        {heroContent.title}
      </motion.h1>

      <motion.p
        className="hero-description"
        initial={{ opacity: 0, y: 15 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{
          delay: 0.45,
          duration: 0.6,
        }}
      >
        {heroContent.description}
      </motion.p>

      <motion.div
        className="hero-buttons"
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{
          delay: 0.6,
        }}
      >
        {heroButtons.map((button, index) => (
          <motion.button
            key={button.label}
            className={`hero-button ${button.variant}`}
            whileHover={{
              y: -2,
              scale: 1.02,
            }}
            whileTap={{
              scale: 0.98,
            }}
            initial={{
              opacity: 0,
              y: 10,
            }}
            animate={{
              opacity: 1,
              y: 0,
            }}
            transition={{
              delay: 0.7 + index * 0.1,
            }}
          >
            {button.label}
          </motion.button>
        ))}
      </motion.div>
    </motion.section>
  );
}

export default Hero;