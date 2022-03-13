
import AsyncStorage from '@react-native-community/async-storage';

module.exports = async (taskData:any) => {
    if(taskData.NetworkState=="On"){
        var count = Number(await AsyncStorage.getItem('NetworkServiceCount-On'));
        await AsyncStorage.setItem('NetworkServiceCount-On',(count+1).toString());
    }else{
        var count = Number(await AsyncStorage.getItem('NetworkServiceCount-Off'));
        await AsyncStorage.setItem('NetworkServiceCount-Off',(count+1).toString());
    }
    console.log('Network Service : ' , taskData);
    // do stuff
    return;
};



