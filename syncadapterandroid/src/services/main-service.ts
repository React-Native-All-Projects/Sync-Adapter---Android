import AsyncStorage from '@react-native-community/async-storage';
import { useContext } from 'react';
import ProviderContext from '../View/provider/context';

module.exports = async (taskData:any) => {
    const callService: any = useContext(ProviderContext);
    callService.sum(taskData);
};



