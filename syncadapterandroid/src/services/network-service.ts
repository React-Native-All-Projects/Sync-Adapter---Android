
import AsyncStorage from '@react-native-community/async-storage';

module.exports = async (taskData:any) => {
    await AsyncStorage.setItem('Value',await AsyncStorage.getItem('Value')+'-Network-'+taskData.NetworkState) ;
    console.log('Network-service : ' , taskData.NetworkState);
    // do stuff
    return;
};



