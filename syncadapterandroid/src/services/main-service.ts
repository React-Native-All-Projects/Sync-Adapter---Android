import AsyncStorage from '@react-native-community/async-storage';

module.exports = async (taskData:any) => {
    var count = Number(await AsyncStorage.getItem('mainServiceCount'));
    await AsyncStorage.setItem('mainServiceCount',(count+1).toString());
    console.log('Main Serive : ' , taskData);
    // do stuff
    return;
};



