function Card({ children, width }) {
  const cardStyle = `flex-col w-[${width}]`;

  return <div className={cardStyle}>{children}</div>;
}

export default Card;
