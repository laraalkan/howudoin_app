import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import AuthNavigator from './AuthNavigator';  // Import the auth navigator

const Stack = createStackNavigator();

const AppNavigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="AuthNavigator">
        <Stack.Screen name="AuthNavigator" component={AuthNavigator} />
        {/* Additional screens can be added here */}
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default AppNavigator;
