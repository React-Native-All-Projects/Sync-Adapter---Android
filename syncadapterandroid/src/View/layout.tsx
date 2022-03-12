import AsyncStorage from "@react-native-community/async-storage";
import React, { useEffect, useState } from "react";
import { NativeModules, Text, TouchableOpacity, View } from "react-native";

const Layout = () =>{
  
  const [count,setCount] = useState('');
  const { ModuleApp } = NativeModules;
  const onStartService = async () => {
    console.log('Run Service');
    await ModuleApp.startService();
  }
  const onEndService = async () => {
    console.log('Run Service');
    await ModuleApp.stopService();
  }
  const onRunBackgroundService = async () => {    
    console.log('Run Background Service');
    await ModuleApp.startBackgroundWork();
  }
  
  const onStopBackgroundService = async () => {
    console.log('Stop Background Service');
    await ModuleApp.stopBackgroundWork();
  }  

  useEffect(()=>{
    setInterval(async()=>{
      var Value = await AsyncStorage.getItem('Value');
      var string = Value?Value:'';
      setCount(string?string:'')
      console.log('setInterval : ' , string);
    },5000)
  },[])

  return (
    <View style={{flex:1,justifyContent:'center',alignItems:'center'}}>
      <Text>App</Text>

      <TouchableOpacity onPress={onStartService}>
        <Text>Start Service</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={onEndService}>
        <Text>End Service</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={onRunBackgroundService}>
        <Text>Run Background Service</Text>
      </TouchableOpacity>
      <Text>{count}</Text>
      <TouchableOpacity onPress={onStopBackgroundService}>
        <Text>Stop Background Service</Text>
      </TouchableOpacity>
      {/* <Text>{Value}</Text> */}
    </View>
  )
}

export default Layout;