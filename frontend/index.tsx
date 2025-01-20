// app/index.tsx
import { useEffect } from 'react';
import { useRouter } from 'expo-router';

export default function Index() {
  const router = useRouter();

  useEffect(() => {
    console.log('Redirecting to welcome screen');
    router.replace('../welcome'); // Redirect to the welcome screen
  }, []);

  return null; // No UI since it immediately redirects
}
