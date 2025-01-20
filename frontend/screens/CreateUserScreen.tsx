import React, { useState } from "react";
import { View, Text, TextInput, Button, Alert, StyleSheet } from "react-native";
import { StackNavigationProp } from '@react-navigation/stack';

// Define your stack parameter list
type RootStackParamList = {
  CreateUser: undefined;
  Home: undefined;
};

// Define the navigation prop type
type CreateUserScreenNavigationProp = StackNavigationProp<
  RootStackParamList,
  'CreateUser'
>;

// Define the props for the CreateUserScreen component
type CreateUserScreenProps = {
  navigation: CreateUserScreenNavigationProp;
};

const CreateUserScreen: React.FC<CreateUserScreenProps> = ({ navigation }) => {
  const [name, setName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const handleCreateUser = () => {
    const backendUrl = "http://localhost:8080/users"; // Replace with your backend URL

    fetch(backendUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: name,
        email: email,
        password: password,
      }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to create user");
        }
        return response.json();
      })
      .then((data) => {
        Alert.alert("Success", `User created: ${data.name}`);
        // Optionally navigate to another screen after successful user creation
        navigation.navigate('Home'); // Navigate to Home screen after success
      })
      .catch((error) => {
        Alert.alert("Error", error.message);
      });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Welcome to HowUDoin</Text>
      <TextInput
        style={styles.input}
        placeholder="Name"
        value={name}
        onChangeText={setName}
      />
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
      <Button title="Create User" onPress={handleCreateUser} />
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

export default CreateUserScreen;
