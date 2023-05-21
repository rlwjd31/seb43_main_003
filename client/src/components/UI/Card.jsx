function Card({ children, width }) {
  return (
    <div style={{ flex: `1 1 ${width}` }} className="grow shrink flex-col">
      {children}
    </div>
  );
}

export default Card;
