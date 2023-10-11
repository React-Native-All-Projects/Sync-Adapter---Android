import AsyncStorage from "@react-native-community/async-storage";
import React, { useEffect } from "react"
import ProviderContext from "./context";

const Provider = (props: any) => {

    useEffect(()=>{
        console.log('Start');
        
    },[])
    const sum = async (taskData:any) => {
        var count = Number(await AsyncStorage.getItem('MainServiceCount'));
        console.log('count :',count);
        
        await AsyncStorage.setItem('MainServiceCount',(count+1).toString());
        console.log('Main Serive : ' , taskData);
    }

    return(
        <>
            <ProviderContext.Provider value={{sum:sum}}>
                {
                    props.children
                }
            </ProviderContext.Provider>
        </>
    )
}

export default Provider;