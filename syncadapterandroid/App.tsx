import React, { useState } from "react";
import Layout from "./src/View/layout";
import Provider from "./src/View/provider/provider";

const App =() =>{
  
  return (
    <>
      <Provider >
        <Layout />        
      </Provider>
    </>
  )
}

export default App;