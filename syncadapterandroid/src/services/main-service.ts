import AsyncStorage from '@react-native-community/async-storage';

module.exports = async (taskData:any) => {
    var count = Number(await AsyncStorage.getItem('MainServiceCount'));
    await AsyncStorage.setItem('MainServiceCount',(count+1).toString());
    console.log('Main Serive : ' , taskData);
};



