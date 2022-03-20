import { StyleSheet } from "react-native";

const layoutStyle = StyleSheet.create({
    title:{
        fontSize:20,fontWeight:'bold',color:'#000'
    },
    text:{
        fontWeight:'bold',color:'#000'
    },
    sectionContainer:{flex:0.2,justifyContent:'center'
        ,width:'95%',borderRadius:10,
    marginVertical:10},
    selfContainer:{flexDirection:'row',justifyContent:'space-between'
    ,marginHorizontal:60},
    itemContainer:{flexDirection:'row',alignItems:'center'},
    textContainer:{flexDirection:'column',justifyContent:'center',alignItems:'center'},
    image:{
        height:30,width:30,margin:10
    },
    mainButtons:{
        margin:10,borderWidth:0.3,padding:5,borderRadius:4
    },
    testButton:{margin:0,height:60,width:60
        ,backgroundColor:'#c5ff0040',alignItems:'center'
        ,justifyContent:'center'}
})


export default layoutStyle;

