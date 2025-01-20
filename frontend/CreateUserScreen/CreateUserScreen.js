/*port React, { useState } from "react";
import { View, Text, TextInput, Button, Alert, StyleSheet } from "react-native"; // Added Text import
import { useNavigation } from "@react-navigation/native";

const CreateUserScreen = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigation = useNavigation(); // Hook to access navigation

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
        Alert.alert("Success", User created: ${data.name});
        navigation.navigate("MainUserScreen", { userId: data.id }); // Navigate to MainUserScreen
      })
      .catch((error) => {
        Alert.alert("Error", error.message);
      });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Welcome to HowUDoin</Text> {/* Corrected Text usage *//*/
      /*extInput
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

export default CreateUserScreen;*/