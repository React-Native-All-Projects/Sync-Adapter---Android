import AsyncStorage from '@react-native-community/async-storage';

module.exports = async (taskData:any) => {
    // if(taskData.State == 'start'){
    //     var count = Number(await AsyncStorage.getItem('MainServiceCount-Start'));
    //    await AsyncStorage.setItem('MainServiceCount-Start',(count+1).toString());
    // }else{
    //     var count = Number(await AsyncStorage.getItem('MainServiceCount-Stop'));
    //     await AsyncStorage.setItem('MainServiceCount-Stop',(count+1).toString());
    // }

    var count = Number(await AsyncStorage.getItem('mainServiceCount'));
    await AsyncStorage.setItem('mainServiceCount',(count+1).toString());
    console.log('Main Serive : ' , taskData);
    // do stuff
    return;
};



