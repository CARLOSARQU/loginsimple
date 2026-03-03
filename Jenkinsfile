pipeline {
    agent any

    environment {
        ANDROID_HOME = "C:\\Users\\Usuario\\AppData\\Local\\Android\\Sdk"
        ANDROID_SDK_ROOT = "C:\\Users\\Usuario\\AppData\\Local\\Android\\Sdk"
        PROYECTO_ANDROID = "C:\\Users\\Usuario\\AndroidStudioProjects\\LoginSimple"
        PROYECTO_APPIUM  = "C:\\Users\\Usuario\\AndroidStudioProjects\\LoginSimpleAutomation"
    }

    stages {

        stage('1. Compilar APK') {
            steps {
                dir("${env.PROYECTO_ANDROID}") {
                    echo '📦 Compilando APK...'
                    bat 'gradlew.bat clean assembleDebug'
                }
            }
        }

        stage('2. Test Unitarios') {
            steps {
                dir("${env.PROYECTO_ANDROID}") {
                    echo '🧪 Ejecutando pruebas unitarias...'
                    bat 'gradlew.bat testDebugUnitTest'
                }
            }
        }

        stage('3. Verificar Dispositivo') {
            steps {
                echo '📱 Verificando celular conectado...'
                bat '%ANDROID_HOME%\\platform-tools\\adb.exe devices'
            }
        }

        stage('4. Iniciar Appium') {
            steps {
                echo '🤖 Iniciando servidor Appium...'
                // Inicia Appium en background y espera que esté listo
                bat 'start /B appium --allow-insecure chromedriver_autodownload'
                sleep 8
            }
        }

        stage('5. Test UI con Appium') {
            steps {
                dir("${env.PROYECTO_APPIUM}") {
                    echo '🚀 Ejecutando pruebas de UI...'
                    bat 'mvn clean test'
                }
            }
        }
    }

    post {
        always {
            echo '🧹 Limpiando recursos...'
            bat 'taskkill /F /IM node.exe /T || exit 0'
        }
        success {
            echo '✅ Pipeline completado exitosamente'
        }
        failure {
            echo '❌ Pipeline falló, revisa los logs'
        }
    }
}
