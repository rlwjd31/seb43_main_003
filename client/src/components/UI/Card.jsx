function Card({ children, width }) {
  return (
    <div style={{ width }} className="flex-col">
      {children}
    </div>
  );
}

export default Card;
