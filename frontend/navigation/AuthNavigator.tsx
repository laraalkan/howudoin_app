import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import LoginScreen from '../screens/LoginScreen';
import CreateUserScreen from '../screens/CreateUserScreen';

const Stack = createStackNavigator();

const AuthNavigator = () => {
  return (
    <Stack.Navigator initialRouteName="Login">
      <Stack.Screen name="Login" component={LoginScreen} />
      <Stack.Screen name="CreateUser" component={CreateUserScreen} />
    </Stack.Navigator>
  );
};

export default AuthNavigator;
