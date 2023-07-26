export default function MyButton({ count, clickHandler }) {
  return <button onClick={clickHandler}> Clicked count {count} times </button>;
}
