import { useState } from "react";

const navLinks = [
  { label: "Features", href: "#features" },
  { label: "Solutions", href: "#solutions" },
  { label: "About", href: "#about" },
  {
    label: "GitHub",
    href: "https://github.com/Pancakes12315/atlas-ai",
  },
];

function Navbar() {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <header className="navbar-wrapper">
      <nav className="navbar">

        <div className="navbar-logo">
          Atlas AI
        </div>

        <button
          className="menu-button"
          onClick={() => setIsOpen(!isOpen)}
        >
          {isOpen ? "✕" : "☰"}
        </button>

        <ul className={`navbar-links ${isOpen ? "open" : ""}`}>
          {navLinks.map((link) => (
            <li key={link.label}>
              <a href={link.href}>
                {link.label}
              </a>
            </li>
          ))}
        </ul>

        <div className={`navbar-actions ${isOpen ? "open" : ""}`}>
          <button className="signin-button">
            Sign In
          </button>

          <button className="primary-button">
            Get Started
          </button>
        </div>

      </nav>
    </header>
  );
}

export default Navbar;