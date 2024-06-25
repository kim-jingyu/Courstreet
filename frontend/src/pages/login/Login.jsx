import { useNavigate } from 'react-router-dom';
import { Modal } from 'antd';

import './Login.style';
import {
  LoginWrapper,
  InputField,
  Links,
  LoginButton,
  LoginContainer,
  LoginForm,
  Options,
  SocialButton,
  SocialLogin,
  Title,
} from './Login.style';
import { useState } from 'react';

function Login() {
  const [flag, setFlag] = useState(false);
  const navigate = useNavigate();
  const login = () => {
    event.preventDefault();
    if (!flag) {
      setOpen(true);
      setFlag(!flag);
      return;
    }
    localStorage.setItem('username', 'JADEN');
    navigate('/');
  };

  const [open, setOpen] = useState(false);
  // 모달 오픈
  // const showModal = () => {
  // };
  // 모달 취소 클릭
  const handleOk = () => {
    event.preventDefault();
    setOpen(false);
  };
  const handleCancel = () => {
    event.preventDefault();
    setOpen(false);
  };

  return (
    <>
      <LoginWrapper>
        <LoginContainer>
          <Title>로그인</Title>
          <LoginForm>
            <InputField placeholder="아이디" />
            <InputField type="password" placeholder="비밀번호" />
            <LoginButton onClick={login}>로그인</LoginButton>
          </LoginForm>
          <Options>
            <label>
              <input type="checkbox" />
              자동 로그인
            </label>
            <Links>
              <a href="#">아이디 찾기</a> | <a href="#">비밀번호 찾기</a>
            </Links>
          </Options>
          <SocialLogin>
            <SocialButton className="google-login">
              <img src="https://img.icons8.com/?size=100&id=17949&format=png&color=000000" alt="Google" />
              구글 로그인
            </SocialButton>
            <SocialButton className="kakao-login">
              <img src="https://img.icons8.com/?size=100&id=2951&format=png&color=000000" alt="Kakao" />
              카카오 로그인
            </SocialButton>
            <br /> <br />
            <LoginButton style={{ width: '100%' }} onClick={() => navigate('/signup')}>
              회원가입 하러 가기
            </LoginButton>
          </SocialLogin>
        </LoginContainer>
      </LoginWrapper>

      <Modal open={open} onOk={handleOk} onCancel={handleCancel} footer={null}>
        <strong>아이디 혹은 비밀번호가 일치하지 않습니다.</strong>
      </Modal>
    </>
  );
}

export default Login;
