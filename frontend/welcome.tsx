// app/welcome.tsx
import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

export default function WelcomeScreen() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Welcome to the App!</Text>
      <Button title="Get Started" onPress={() => console.log('Button Pressed')} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },
  title: {
    fontSize: 24,
    marginBottom: 20,
  },
});
