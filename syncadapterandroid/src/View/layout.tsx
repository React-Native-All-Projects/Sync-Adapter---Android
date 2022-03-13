import AsyncStorage from "@react-native-community/async-storage";
import React, { useEffect, useState } from "react";
import { Image, NativeModules, Text, TouchableOpacity, View } from "react-native";
import layoutStyle from "./layout.style";

const Layout = () =>{
  
  const [model,setModel] = useState({
    mainServiceCount:'0',
    NetworkServiceCountOn:'0',
    NetworkServiceCountOff:'0'
  });

  const { ModuleApp } = NativeModules;
  const onStartService = async () => {
    await ModuleApp.startService();
  }
  const onStartBackgroundService = async () => {    
    await ModuleApp.startBackgroundWork();
  }
  
  const onStopBackgroundService = async () => {
    await ModuleApp.stopBackgroundWork();
  }  

  useEffect(()=>{
    setInterval(async()=>{
      var mainServiceCount = await AsyncStorage.getItem('mainServiceCount') ?? '0';
      var NetworkServiceOn = await AsyncStorage.getItem('NetworkServiceCount-On') ?? '0';
      var NetworkServiceOff = await AsyncStorage.getItem('NetworkServiceCount-Off') ?? '0';

      setModel({
        mainServiceCount:mainServiceCount,
        NetworkServiceCountOn:NetworkServiceOn,
        NetworkServiceCountOff:NetworkServiceOff
      })
    },2000)
  },[])

  return (
    <View style={{flex:1,justifyContent:'center',alignItems:'center'}}>
      <View style={{flex:0.2}}></View>
      <View style={{flex:0.2}}><Text style={[layoutStyle.title]}>Sync Adapter For Android</Text></View>

      <View style={[layoutStyle.sectionContainer,{backgroundColor:'#003cff47'}]}>
        <View style={[layoutStyle.selfContainer]}>
          <View style={[layoutStyle.textContainer]}>
            <Text style={[layoutStyle.text]}>On</Text>
            <Text style={[layoutStyle.text]}>{model.NetworkServiceCountOn}</Text>
          </View>
          <View style={[layoutStyle.textContainer]}>
            <Text style={[layoutStyle.text]}>Off</Text>
            <Text style={[layoutStyle.text]}>{model.NetworkServiceCountOff}</Text>
          </View>
          <View style={[layoutStyle.itemContainer]}>
            <View>
              <Text style={[layoutStyle.text]}>Network</Text>
              <Image 
                source={require('../../assets/images/computer.png')}
                style={[layoutStyle.image]}
              />
            </View>
          </View>
        </View>
      </View>

      <View style={[layoutStyle.sectionContainer,{backgroundColor:'#ff000047'}]}>
        <View style={[layoutStyle.selfContainer]}>
        <View style={[layoutStyle.textContainer]}>
            <Text style={[layoutStyle.text]}>Count</Text>
            <Text style={[layoutStyle.text]}>{model.mainServiceCount}</Text>
          </View>
          <View style={{flexDirection:'row'}}>
            <View style={[layoutStyle.itemContainer]}>
              <TouchableOpacity onPress={onStartService}
              style={[layoutStyle.mainButtons,layoutStyle.testButton]}
              ><Text style={[layoutStyle.text]}>Test</Text></TouchableOpacity>
            </View>
            <View style={[layoutStyle.itemContainer,{flexDirection:'column'}]}>
                <View>
                  <Text style={[layoutStyle.text]}>Worker</Text>
                  <Image 
                    source={require('../../assets/images/helmet.png')}
                    style={[layoutStyle.image]}
                  />
                </View>
                <View style={{width:'100%',flexDirection:'row'}}>
                  <TouchableOpacity onPress={onStopBackgroundService}
                  style={[layoutStyle.mainButtons,{backgroundColor:'#ff0000b0'}]}
                  ><Text style={[layoutStyle.text]}>Stop</Text></TouchableOpacity>
                  <TouchableOpacity onPress={onStartBackgroundService}
                  style={[layoutStyle.mainButtons,{backgroundColor:'#00ff1db0'}]}
                  ><Text style={[layoutStyle.text]}>Start</Text></TouchableOpacity>
                </View>
            </View>
          </View>
        </View>
      </View>

      <View style={{flex:0.2}}></View>
    </View>
  )
}

export default Layout;