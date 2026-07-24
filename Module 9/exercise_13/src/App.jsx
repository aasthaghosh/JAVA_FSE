import React from 'react'
import useCounter from './useCounter'

function App() {
  const [count, increment] = useCounter(0)

  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={increment}>Increment</button>
    </div>
  )
}

export default App
