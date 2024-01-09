//package com.abcoding.connect.presentation.components
//
//import androidx.compose.material3.BottomSheetDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//
//@Composable
//fun BottomSheetDrawer(){
//    var showSheet by remember { mutableStateOf(false) }
//
//    if (showSheet){
//        BottomSheet(){
//            showSheet = false
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BottomSheet(onDismiss: () -> Unit) {
//    val modalBottomSheetState = rememberModalBottomSheetState()
//    ModalBottomSheet(
//        onDismissRequest = {onDismiss() },
//        sheetState = modalBottomSheetState,
//        dragHandle = {BottomSheetDefaults.DragHandle(
//
//        )}
//    ) {
//
//    }
//}
