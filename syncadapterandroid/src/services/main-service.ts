import AsyncStorage from '@react-native-community/async-storage';

module.exports = async (taskData:any) => {
    await AsyncStorage.setItem('Value',await AsyncStorage.getItem('Value')+'-Main-'+taskData.State) ;
    console.log('main-task : ' , taskData.State);
    // do stuff
    return;
};



