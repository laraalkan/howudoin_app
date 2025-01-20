// app/login.tsx
import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

export default function LoginScreen() {
  return (
    <View style={styles.container}>
      <Text>Login Screen</Text>
      <Button title="Login" onPress={() => console.log('Login pressed')} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});
