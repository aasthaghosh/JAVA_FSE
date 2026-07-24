import { useState } from 'react'

function useCounter(initialValue = 0) {
  const [count, setCount] = useState(initialValue)
  const increment = () => setCount((prev) => prev + 1)
  return [count, increment]
}

export default useCounter
