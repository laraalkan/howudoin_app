import React, { useState } from "react";
import { View, Text, TextInput, Button, Alert, StyleSheet } from "react-native";
import { StackNavigationProp } from '@react-navigation/stack';

// Define your stack parameter list
type RootStackParamList = {
  Login: undefined;
  Home: undefined;
};

// Define the navigation prop type
type LoginScreenNavigationProp = StackNavigationProp<
  RootStackParamList,
  'Login'
>;

// Define the props for the LoginScreen component
type LoginScreenProps = {
  navigation: LoginScreenNavigationProp;
};

const LoginScreen: React.FC<LoginScreenProps> = ({ navigation }) => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const handleLogin = () => {
    const backendUrl = "http://localhost:8080/login"; // Replace with your backend URL

    fetch(backendUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: email,
        password: password,
      }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to log in");
        }
        return response.json();
      })
      .then((data) => {
        Alert.alert("Success", `Welcome back, ${data.name}`);
        // Optionally navigate to another screen after successful login
        navigation.navigate('Home'); // Navigate to Home screen after login
      })
      .catch((error) => {
        Alert.alert("Error", error.message);
      });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Login to HowUDoin</Text>
      <TextInput
        style={styles.input}
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
      />
      <TextInput
        style={styles.input}
        placeholder="Password"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />
      <Button title="Login" onPress={handleLogin} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    justifyContent: "center",
  },
  header: {
    fontSize: 24,
    fontWeight: "bold",
    textAlign: "center",
    marginBottom: 20,
  },
  input: {
    height: 40,
    borderColor: "#ccc",
    borderWidth: 1,
    marginBottom: 12,
    paddingHorizontal: 8,
  },
});

export default LoginScreen;
