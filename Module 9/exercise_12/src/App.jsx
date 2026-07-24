import React, { createContext, useContext } from 'react'

const MyContext = createContext()

function Child() {
  const value = useContext(MyContext)
  return (
    <div>
      {value}
    </div>
  )
}

function App() {
  return (
    <MyContext.Provider value="Hello from Context!">
      <Child />
    </MyContext.Provider>
  )
}

export default App
