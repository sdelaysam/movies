cmake_minimum_required(VERSION 3.6)

set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS} -g -O0 -std=c++17 -fexceptions")
set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS} -O3 -std=c++17 -fexceptions")

set(CPP_DIR ../../cpp)
set(OUT_CPP_DIR ../../idl/generated/out-cpp)
set(OUT_JNI_DIR ../../idl/generated/out-jni)
set(SUPPORT_JNI_DIR ../../tools/djinni/support-lib/jni)

file(GLOB_RECURSE CPP_SRC ${CPP_DIR}/*.hpp ${CPP_DIR}/*.cpp)
file(GLOB OUT_CPP_SRC ${OUT_CPP_DIR}/*)
file(GLOB OUT_JNI_SRC ${OUT_JNI_DIR}/*)
file(GLOB SUPPORT_JNI_SRC ${SUPPORT_JNI_DIR}/*)

add_library(android-lib SHARED
        ${CPP_SRC}
        ${OUT_CPP_SRC}
        ${OUT_JNI_SRC}
        ${SUPPORT_JNI_SRC})
include_directories(android-lib
        ${CPP_DIR}
        ${OUT_CPP_DIR}
        ${OUT_JNI_DIR}
        ${SUPPORT_JNI_DIR})
