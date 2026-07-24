import React, { useEffect } from 'react'

function App() {
  useEffect(() => {
    console.log('Component loaded')
  }, [])

  return (
    <div>
      Component Rendered
    </div>
  )
}

export default App
