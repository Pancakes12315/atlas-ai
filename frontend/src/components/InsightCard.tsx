import { motion } from "framer-motion";

type InsightCardProps = {
  icon: string;
  severity: string;
  confidence: string;
  title: string;
  description: string;
  action: string;
};

function InsightCard({
  icon,
  severity,
  confidence,
  title,
  description,
  action,
}: InsightCardProps) {
  return (
    <motion.div
      className={`insight-card ${severity}`}
      initial={{
        opacity: 0,
        y: 25,
      }}
      whileInView={{
        opacity: 1,
        y: 0,
      }}
      viewport={{
        once: true,
        amount: 0.2,
      }}
      transition={{
        duration: 0.5,
      }}
      whileHover={{
        y: -8,
        scale: 1.02,
      }}
    >
      <div className="insight-title">
        <div className="insight-icon">
          {icon}
        </div>

        <div>
          <h3>{title}</h3>

          <span className="confidence">
            Confidence {confidence}
          </span>
        </div>
      </div>

      <p>{description}</p>

      <div className="insight-section">
        <h4>Suggested Action</h4>

        <p>{action}</p>
      </div>
    </motion.div>
  );
}

export default InsightCard;